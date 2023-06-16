'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
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
      'tour_categories',
      [
        {
          "id": "568024bb-641d-41a3-9791-37f42baed746",
          "name": "Cagar Alam",
          "createdAt": new Date(),
          "updatedAt": new Date()
        },
        {
          "id": "7890e53f-bece-438f-a82a-32a067faf8fb",
          "name": "Budaya",
          "createdAt": new Date(),
          "updatedAt": new Date()
        },
        {
          "id": "8a3d49eb-a9ca-43ac-87cb-cdbe959cdaf9",
          "name": "Taman Hiburan",
          "createdAt": new Date(),
          "updatedAt": new Date()
        },
        {
          "id": "8c359fa2-d576-44f4-a2d3-ed9e6574bc63",
          "name": "Pusat Perbelanjaan",
          "createdAt": new Date(),
          "updatedAt": new Date()
        },
        {
          "id": "e1004438-0b73-4c9e-8b64-5f36285c711c",
          "name": "Bahari",
          "createdAt": new Date(),
          "updatedAt": new Date()
        },
        {
          "id": "e13348f5-233e-44df-9c55-a821cd969b52",
          "name": "Tempat Ibadah",
          "createdAt": new Date(),
          "updatedAt": new Date()
        }
      ]
    )
  },

  async down(queryInterface, Sequelize) {
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};
