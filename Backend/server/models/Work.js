const mongoose = require('mongoose');
const Schema =mongoose.Schema

const WorkSchema = new Schema({
    ngay_dang_ky: {
        type: Date,
        default: Date.now
      },
    ca_dang_ky: {
        type: Number,
        required: true
      },
    so_gio_lam: {
        type: Number,
        required: true
      },
    he_so: {
        type: Number,
        required: true
    },
    tinh_trang: {
        type: String,
        enum: ['daduyet', 'choduyet', 'khongduyet']
      },
    user: {
        type: Schema.Types.ObjectId,
        ref: 'users'
    }
})

module.exports = mongoose.model('works', WorkSchema)