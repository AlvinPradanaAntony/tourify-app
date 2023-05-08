import { Router } from 'express'
import { postLogin } from '../controllers/auth/auth.controller'

export const loadAuthRouter = app => {
    const router = Router()
    router.route('/login').get(postLogin)

    app.use('/auth', router)
}