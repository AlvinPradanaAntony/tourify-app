import { Storage } from '@google-cloud/storage'
import path from 'path'

const serviceKey = path.join(__dirname, './../../service-account-keys.json')
const storage = new Storage({
    keyFilename: serviceKey,
    projectId: 'tourify-projects-389411'
})

module.exports = storage