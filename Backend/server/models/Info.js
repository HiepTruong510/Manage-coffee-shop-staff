const mongoose = require('mongoose');
const Schema =mongoose.Schema

const InfoSchema = new Schema({
    permission: {
        type: String
    },
    fullname: {
        type: String,
        required: true,
        unique: true
    },
    phonenumber: {
        type: String,
        required: true
    },
    email: {
        type: String
    },
    birthyear: {
        type: String
    },
    address: {
        type: String
    },
    more: {
        type: String
    },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'users'
    }
})

module.exports = mongoose.model('infos', InfoSchema)