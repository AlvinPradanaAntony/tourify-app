import { Router } from 'express'
import multer from 'multer'

// controllers
import { login, register, refreshToken, verifyToken } from '../controllers/auth/auth.controller'
import { getAllUsers, createUsers, showUsers, updateUsers, deleteUsers } from '../controllers/user/user.controller'
import { getAllTourCategory, storeTourCategory, showTourCategory, updateTourCategory, deleteTourCategory } from '../controllers/tour_category/tour_category.controller'
import { deleteTourDestination, getAllTourDestination, showTourDestination, storeTourDestination, updateTourDestination } from '../controllers/tourist_destination/tourist_destination.controller'
import { getRatingWhereUserAndTouristDestination, getRatingWhereTouristDestination, storeRating, getAllRating } from '../controllers/rating/rating.controller'

// validations
import { loginValidation, registerValidation } from '../validations/auth.validation'

// middleware
import authenticatedMiddleware from '../middlewares/authenticated'
import imgUploadMiddleware from '../middlewares/upload-image'

const multerMid = multer({
    storage: multer.memoryStorage(),
    fileFilter: function (req, file, callback) {
        const ext = path.extname(file.originalname);
        if (ext !== '.jpg' && ext !== '.png' && ext !== '.jpeg') {
            return callback(new Error('Only Image Files are supported'))
        }
        callback(null, true)
    },
    limits: {
        fileSize: 2 * 1024 * 1024
    }
})

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
            .post(imgUploadMiddleware(multerMid.single('picture')), createUsers)
    router.route('/:id')
            .get(showUsers)
            .post(imgUploadMiddleware(multerMid.single('picture')), updateUsers)
            .delete(deleteUsers)

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
    router.use(authenticatedMiddleware)
    router.route('/')
            .get(getAllTourDestination)
            .post(storeTourDestination)
    router.route('/:id')
            .get(showTourDestination)
            .post(updateTourDestination)
            .delete(deleteTourDestination)
    
    app.use('/tourist-destination', router)
}

export const loadRatingRouter = app => {
    const router = Router()
    router.use(authenticatedMiddleware)
    router.route('/with-user-and-tour-destination').get(getRatingWhereUserAndTouristDestination)
    router.route('/with-tour-destination').get(getRatingWhereTouristDestination)
    router.route('/')
            .get(getAllRating)
            .post(storeRating)

    app.use('/ratings', router)
}