import { Router } from 'express'

// controllers
import { login, register, refreshToken, verifyToken } from '../controllers/auth/auth.controller'
import { getAllUsers, createUsers, showUsers } from '../controllers/user/user.controller'
import { getAllTourCategory, storeTourCategory, showTourCategory, updateTourCategory, deleteTourCategory } from '../controllers/tour_category/tour_category.controller'
import { test } from '../controllers/tourist_destination/tourist_destination.controller'

// validations
import { loginValidation, registerValidation } from '../validations/auth.validation'

import authenticatedMiddleware from '../middlewares/authenticated'
import { body } from 'express-validator'

export const loadAuthRouter = app => {
    const router = Router()
    router.route('/login').post(...loginValidation, login)
    router.route('/register').post(...registerValidation, register)

    router.use(authenticatedMiddleware)
    router.route('/verify').get(verifyToken)
    router.route('/refresh').post(refreshToken)

    app.use('/auth', router)
}

export const loadUserRouter = app => {
    const router = Router()
    router.use(authenticatedMiddleware)
    router.route('/')
            .get(getAllUsers)
            .post(createUsers)
    router.route('/:id')
            .get(showUsers)

    app.use('/users', router)
}

export const loadTourCategoryRouter = app => {
    const router = Router()
    router.use(authenticatedMiddleware)
    router.route('/')
            .get(getAllTourCategory)
            .post(storeTourCategory)
    router.route('/:id')
            .get(showTourCategory)
            .post(updateTourCategory)
            .delete(deleteTourCategory)

    app.use('/tour-categories', router)
}

export const loadTouristDestinationRouter = app => {
    const router = Router()
    router.route('/:input')
            .get(test)
    
    app.use('/tourist-destination', router)
}