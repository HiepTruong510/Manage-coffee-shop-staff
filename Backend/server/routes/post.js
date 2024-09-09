const express = require('express');
const router =  express.Router()
const verifyToken = require('../middleware/auth')

const Post = require('../models/Post');

//xem
// @route GET api/posts
// @desc Get posts
// @access Private
router.get('/', verifyToken, async(req, res) => {
    try {
        const posts = await Post.find({user: req.userId,
                                        gio: {
                                        $gte: "2023-06-11T00:00:00.000Z",
                                        $lt: "2023-06-12T10:00:00.000Z"
                                        }}).populate('user',['username'])
        res.json({success: true, posts })
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    
})

//them
// @route POST api/posts
// @desc Create post
// @access Private
router.post('/', verifyToken, async(req, res) => {
    const{title, decription, url,gio, status} = req.body

    //Simple validation
    if(!title)
        return res.status(400).json({success: false, message: 'title is required'})
    
    try {
        const newPost =  new Post({
            title, 
            decription, 
            url: (url.startsWith('https://')) ? url : `https://${url}`, 
            status: status || 'To Learn', 
            gio: gio,
            user: req.userId //'6462d5334f9224b0796edc03'
        })

        await newPost.save()

        res.status(400).json({success: true, message: 'Post success', post: newPost})

    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
})


//sua
// @route PUT api/posts
// @desc Update posts
// @access Private
router.put('/:id', verifyToken, async(req, res) => {
    const{title, decription, url, status} = req.body

    //Simple validation
    if(!title)
        return res.status(400).json({success: false, message: 'title is required'})
    
    try {
        let updatedPost =  {
            title, 
            decription: decription || '', 
            url: (url.startsWith('https://') ? url : `https://${url}`) || '', 
            status: status || 'To Learn', 
            
        }

       const  postUpdateCondition   = {_id: req.params.id, user: req.userId}
       updatedPost = await Post.findOneAndUpdate(postUpdateCondition,updatedPost, {new: true})

        //user not authorised to update post 
        if(!updatedPost)
            return res.status(401).json({success: false, message: 'Post not found or user not authorised'})

        res.json({success: true, message: 'Excellent progress!', post: updatedPost})
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    
})



//xoa
// @route DELETE api/posts
// @desc Delete posts
// @access Private
router.delete('/:id', verifyToken, async(req, res) => {
    const{title, decription, url, status} = req.body

    try {
       const  postDeleteCondition   = {_id: req.params.id, user: req.userId}
       const deletedPost = await Post.findOneAndDelete(postDeleteCondition)

        //user not authorised to update post 
        if(!deletedPost)
            return res.status(401).json({success: false, message: 'Post not found or user not authorised'})

            res.json({success: true, message: 'Delete success', post: deletedPost})
    } catch (error) {
        console.log(error)
        res.status(500).json({success: false, message: 'Internal server error'})
    }
    
})


module.exports = router