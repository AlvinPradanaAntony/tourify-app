const mysql = require('mysql')

const con = mysql.createConnection({
    socketPath: '/cloudsql/tourify-projects:us-central1:tourify-mysql-instance',
    username: 'root',
    password: 'tourifyapp123',
    database: 'tourify_db'
})

con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
});