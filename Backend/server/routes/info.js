const express = require('express');
const router =  express.Router()

const Info = require('../models/Info');
const verifyToken = require('../middleware/auth');

//----------------------------------GET--------------------------
// admin xem danh sach nhan vien
// @route GET api/infos
// @desc Get infos
// @access Private
router.get('/list', verifyToken, async(req, res) => {
    try {
        const infos = await Info.find({ permission: "nhanvien"}).populate('user',['username']) //, permission: "nhanvien"
        res.json({success: true, message:'Get Info list success', infos })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})

// admin lấy tên nhân viên từ id
router.post('/fullname', verifyToken, async(req, res) => {
    const{iduser} = req.body
    try {
        const infos = await Info.findOne({user: iduser})
        res.json({success: true, message:'Get Fullname_employ success', infos })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})

// tự xem thông tin cá nhân
// @route GET api/infos/person
// @desc Get infos/person
// @access Private
router.get('/person', verifyToken, async(req, res) => {
    try {
        const infos = await Info.find({ user: req.userId }).populate('user',['username']) //, permission: "nhanvien"
        res.json({success: true, message:"get info person success", infos })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    //permission: permission || 'nhanvien'  
})

// kiểm tra permisson, chưa dùng
// @route GET api/infos/per
// @desc Get infos/per
// @access Private
router.get('/permission', verifyToken, async(req, res) => {
    try {
        /*const checkpermisstion = await Info.findOne({username})
        const nhanvien = await argon2.verify(user.password, "nhanvien")
        cate = "nhanvien"; 
        if(!nhanvien)
            cate = "quanly"; */
        const infos = await Info.find({user: req.userId}).select('permission') 
        res.json({success: true,message:'Get Info permission success', infos })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})

//----------------------------------POST--------------------------
//nv tự them
// @route POST api/infos
// @desc Create infos
// @access Private
router.post('/', verifyToken, async(req, res) => {
    const{ fullname, phonenumber, email, birthyear, address, more} = req.body
    //const myObject = { myField: encodeURIComponent(fullname) };
    //check fullname
    const info = await Info.findOne({ fullname })
        if (info)
            return res
            .status(400)
            .json({success: false, message: 'fullname already taken '})

    try {
        const newInfo =  new Info({
            permission: "nhanvien", 
            fullname: fullname, 
            phonenumber: phonenumber, 
            email: email || '', 
            birthyear: birthyear || '',
            address: address || '',
            more: more || '',
            user: req.userId //'64513e02484c7989c54b3466'
        })

        await newInfo.save()

        res.status(200).json({success: true, message: 'Post Info success', infos: newInfo})

    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})

//----------------------------------PUT-----------------------------
//nhân viên tự sửa của mình 
// @route PUT api/infos
// @desc Update infos
// @access Private
router.put('/:id', verifyToken, async(req, res) => {
    const{ fullname, phonenumber, email,birthyear, address, more} = req.body

    //Simple validation
    try {
        let updatedInfo =  {
            fullname: fullname, 
            phonenumber: phonenumber, 
            email: email || '', 
            birthyear: birthyear || '',
            address: address || '',
            more: more || ''
        }

       const  infoUpdateCondition = {_id: req.params.id, user: req.userId}
       updatedInfo = await Info.findOneAndUpdate(infoUpdateCondition,updatedInfo, {new: true})

        //user not authorised to update info 
        if(!updatedInfo)
            return res.status(401).json({success: false, message: 'Info not found or user not authorised'})

        res.json({success: true, message: 'Put Info success!', infos: updatedInfo})
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    
})

//----------------------------------------DEL---------------------
// admin xóa nhân viên
// @route DELETE api/infos
// @desc Delete infos
// @access Private
router.delete('/:id', verifyToken, async(req, res) => {
    const{} = req.body

    try {
       const  infoDeleteCondition   = {_id: req.params.id}
       const deletedInfo = await Info.findOneAndDelete(infoDeleteCondition)

        //user not authorised to update post 
        if(!deletedInfo)
            return res.status(401).json({success: false, message: 'Delete not found or user not authorised'})

        res.json({success: true, message: 'Delete info success', info: deletedInfo})
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    
})


module.exports = router