'use strict';

const { genSalt, hash } = require('bcrypt');

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    /**
     * Add seed commands here.
     *
     * Example:
     * await queryInterface.bulkInsert('People', [{
     *   name: 'John Doe',
     *   isBetaMember: false
     * }], {});
    */
   return queryInterface.bulkInsert(
    'users', 
    [
      {
        id: 'bd4faa79-04b8-4c89-8003-43330b1a9fc2',
        name: 'Michael Subakier',
        email: 'subakier07@gmail.com',
        username: 'michael77',
        password: await hash('1234', await genSalt()),
        phone: '0812345678',
        address: 'lorem ipsum dolor sit amet',
        role: 'Visitor',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]
   )
  },

  async down (queryInterface, Sequelize) {
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
    return queryInterface.bulkDelete('users', null, {
      truncate: true,
      restartIdentity: true
    })
  }
};
