const express = require('express');
const router =  express.Router()

const Work = require('../models/Work');
const verifyToken = require('../middleware/auth');


//----------------------------------POST--------------------------
//nv đk work
// @route POST api/works
// @desc Create works
// @access Private
router.post('/', verifyToken, async(req, res) => {
    const{ngay_dang_ky, ca_dang_ky, tinh_trang} = req.body
    try {
        const newWork =  new Work({
            ngay_dang_ky: ngay_dang_ky,
            ca_dang_ky: ca_dang_ky,
            so_gio_lam: 4,
            he_so: 1.0,
            tinh_trang: tinh_trang || "choduyet",
            user: req.userId 
        })

        await newWork.save()

        res.status(400).json({success: true, message: 'Post Work success', works: newWork})

    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})

//----------------------------------PUT-----------------------------
//admin sửa lại tình trạng
// @route PUT api/infos
// @desc Update infos
// @access Private
router.put('/:id', verifyToken, async(req, res) => {
    const{tinh_trang} = req.body

    //Simple validation
    try {
        let updatedWork =  {
            tinh_trang: tinh_trang
        }

       const  workUpdateCondition = {_id: req.params.id}  //, userId: '6463325898a111937f6e3a4f' : req.userId
       updatedWork = await Work.findOneAndUpdate(workUpdateCondition,updatedWork, {new: true})

        if(!updatedWork)
            return res.status(401).json({success: false, message: 'Work not found or work not authorised'})

        res.json({success: true, message: 'Put Work success!', work: updatedWork})
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    
})

//----------------------------------------DEL---------------------
//nhan vien huy dang ky
// @route DELETE api/infos
// @desc Delete infos
// @access Private
router.delete('/:id', verifyToken, async(req, res) => {
    const{} = req.body

    try {
       const  workDeleteCondition   = {_id: req.params.id, user: req.userId}
       const deletedWork = await Work.findOneAndDelete(workDeleteCondition)

        //user not authorised to update post 
        if(!deletedWork)
            return res.status(401).json({success: false, message: 'Work not found or work not authorised'})

        res.json({success: true, message: 'Delete work success', works: deletedWork})
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    
})

//----------------------------------GET--------------------------
//xem danh sach công việc của nhân viên
// @route GET api/infos
// @desc Get infos
// @access Private
router.post('/list', verifyToken, async(req, res) => {
    const{ngay_dang_ky,ca_dang_ky, tinh_trang} = req.body
    try {
        //const works = await Work.find({ngay_dang_ky: ngay_dang_ky}).populate('user', 'username')
        const works = await Work.aggregate([
            {   $match: 
                { 
                    //ngay_dang_ky: ngay_dang_ky//
                    ca_dang_ky: ca_dang_ky,
                    tinh_trang: tinh_trang
                }
            },
            {
                $lookup: {
                from: 'infos',
                localField: 'user',
                foreignField: 'user',
                as: 'user_info'}
            },
            {
                $project: {
                _id: 1,
                ngay_dang_ky: 1,
                ca_dang_ky: 1,
                so_gio_lam: 1,
                he_so:1,
                tinh_trang:1,
                'user_info.fullname': 1}
            }
            ]);
        //const info = await Info.find({})
        res.json({success: true, message:"get info person success", works })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})

//xem danh sach công việc của nhân viên
// @route GET api/infos
// @desc Get infos
// @access Private
router.post('/list2', verifyToken, async(req, res) => {
    const{ngay_dang_ky,ca_dang_ky, tinh_trang} = req.body
    try {
        const works = await Work.find({ngay_dang_ky: ngay_dang_ky,
                    ca_dang_ky: ca_dang_ky,
                    tinh_trang: tinh_trang})//.populate('user', 'username')
        
        res.json({success: true, message:"get work list success", works })
    } catch (error) {                   
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})

//xem công việc cá nhân
// @route GET api/works/person
// @desc Get works/person
// @access Private
router.post('/person', verifyToken, async(req, res) => {
    const{ngay_dang_ky} = req.body
    try {
        const works = await Work.find({user: req.userId, ngay_dang_ky: ngay_dang_ky}).populate('user',['username']) 
        res.json({success: true, message:"get info person success", works })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})


//check xem mình đã đăg ký ca này chưa
// @route GET api/works/person
// @desc Get works/person
// @access Private
router.post('/person/check', verifyToken, async(req, res) => {
    const{ngay_dang_ky,ca_dang_ky} = req.body
    try {
        const works = await Work.find({user: req.userId, ngay_dang_ky: ngay_dang_ky, ca_dang_ky: ca_dang_ky})
        res.json({success: true, message:"Check work success", works })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})


module.exports = router