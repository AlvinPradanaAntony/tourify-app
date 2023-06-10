export default function (multerUploadFunction) {
    return function(req, res, next) {
        multerUploadFunction(req, res, err => {
            if (err && err.name && err.name == 'MulterError') {
                return res.status(422).json({
                    message: err.message,
                }); 
            }
            if (err) {
                return res.status(422).json({
                    message: err.message,
                }); 
            }
            next();
        });
    };
}