const {createPool} = require('mysql')

const pool = createPool({
    host: 'localhost',
    user: 'root',
    password: 'Doura@123',
    connectionLimit: 50
})

module.exports = pool
