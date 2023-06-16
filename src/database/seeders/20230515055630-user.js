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
      },
      {
        id: 'bd4faa79-04b8-4c89-8003-772ujhu27717',
        name: 'Hanoi Tanaka',
        email: 'hh07@gmail.com',
        username: 'han56',
        password: await hash('1234', await genSalt()),
        phone: '0812345678',
        address: 'lorem ipsum dolor sit amet',
        role: 'Visitor',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: 'bd4faa79-04b8-1827-8003-28817b1a9fc2',
        name: 'Sutanto',
        email: 'tan87@gmail.com',
        username: 'tan87',
        password: await hash('1234', await genSalt()),
        phone: '0812345678',
        address: 'lorem ipsum dolor sit amet',
        role: 'Visitor',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: 'a73naa79-18w8-4c89-8003-43330b1a99o3',
        name: 'Jones Santoso',
        email: 'jon07@gmail.com',
        username: 'jones07',
        password: await hash('1234', await genSalt()),
        phone: '0812345678',
        address: 'lorem ipsum dolor sit amet',
        role: 'Visitor',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: 'a73n89sa-18w8-4c89-8003-43330b1a99o3',
        name: 'Kim Hang Seo',
        email: 'seo47@gmail.com',
        username: 'seo47',
        password: await hash('1234', await genSalt()),
        phone: '0812345678',
        address: 'lorem ipsum dolor sit amet',
        role: 'Visitor',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: 'a73naa79-18w8-4c89-8003-71yhs88293e3',
        name: 'Alan Sayuri',
        email: 'sayur13@gmail.com',
        username: 'sayur13',
        password: await hash('1234', await genSalt()),
        phone: '0812345678',
        address: 'lorem ipsum dolor sit amet',
        role: 'Visitor',
        createdAt: new Date(),
        updatedAt: new Date()
      },
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
