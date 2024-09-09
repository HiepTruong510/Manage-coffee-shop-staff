require('dotenv').config()
const express = require('express')
const mongoose = require('mongoose')

const authRouter = require('./routes/auth')
const postRouter = require('./routes/post')
const infoRouter = require('./routes/info')
const workRouter = require('./routes/work')
const adjustRouter = require('./routes/adjust')
const salaryRouter = require('./routes/salary')
const connetcDB = async () => {
    try
    {
        await mongoose.connect(`mongodb+srv://${process.env.DB_USERNAME}:${process.env.DB_PASSWORD }@mernn.prpeimp.mongodb.net/mernn?retryWrites=true&w=majority`,
        {
            useNewUrlParser: true
        }
        )

        console.log('mongoDB connected')
    }catch (error){
        console.log(error.message)
        process.exit(1)
    }
}

connetcDB()

const app = express()
app.use(express.json())

//app.get('/', (req, res) => res.send('hello word'))
app.use('/api/auth', authRouter)
app.use('/api/posts', postRouter)
app.use('/api/infos', infoRouter)
app.use('/api/works', workRouter)
app.use('/api/adjusts', adjustRouter)
app.use('/api/salarys', salaryRouter)

const PORT = 5000

app.listen(PORT, () => console.log(`server start on port ${PORT}`))

