const mongoose = require('mongoose');
const Schema =mongoose.Schema

const PostSchema = new Schema({
    title: {
        type: String,
        required: true,
    },
    decription: {
        type: String,
    },
    url: {
        type: String
    },
    gio: {
        type: Date,
        default: Date.now
    },
    status: {
        type: String,
        enum: ['To Learn', 'Learning', 'Learned']
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'users'
    }
})

module.exports = mongoose.model('posts', PostSchema)