const express = require('express');
const router =  express.Router()
const verifyToken = require('../middleware/auth')

const Adjust = require('../models/Work');



router.put('/', verifyToken, async(req, res) => { 
    const{ngay_dang_ky, ca_dang_ky, he_so} = req.body

    //Simple validation
    try {
        let updatedAdjust =  {
            he_so: he_so
        }

       const  AdjustUpdateCondition   = {
                                        //ngay_dang_ky: ngay_dang_ky,
                                    
                                        //ca_dang_ky:ca_dang_ky,
                                        tinh_trang: "daduyet"}

       updatedAdjust = await Adjust.updateMany(AdjustUpdateCondition,updatedAdjust, {multi: true, new: true})
        //user not authorised to update post 
        if(!updatedAdjust)
            return res.status(401).json({success: false, message: 'Work not found or user not authorised'})

        res.json({success: true, message: 'Adjust Success!', updatedAdjust})
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    
})


module.exports = router