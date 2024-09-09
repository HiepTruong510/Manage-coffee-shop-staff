const express = require('express');
const router =  express.Router()
const verifyToken = require('../middleware/auth')
const Salary = require('../models/Work');


//----------------------------------GET--------------------------
// Lấy danh sách làm việc của 1 nhân viên
// @route POST api/salarys
// @desc Create salarys
// @access Private
router.post('/', verifyToken, async(req, res) => {
    const{tungay,denngay, iduser} = req.body
    try {
        const salarys = await Salary.find({ user: iduser,
                                        ngay_dang_ky: {
                                            $gte: tungay,
                                            $lt: denngay
                                        },
                                        tinh_trang: "daduyet"
                                    })//.populate('user',['username']) 
        res.json({success: true, message:"get list work employee success", salarys })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})

module.exports = router