import { Router } from 'express'
import { login, refreshToken, verifyToken } from '../controllers/auth/auth.controller'
import authenticatedMiddleware from '../middlewares/authenticated'

export const loadAuthRouter = app => {
    const router = Router()
    router.route('/login').post(login)

    router.use(authenticatedMiddleware)
    router.route('/verify').get(verifyToken)
    router.route('/refresh').post(refreshToken)

    app.use('/auth', router)
}