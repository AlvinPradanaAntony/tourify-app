'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('ratings', {
      id: {
        allowNull: false,
        primaryKey: true,
        type: Sequelize.UUID
      },
      user_id: {
        type: Sequelize.UUID,
        foreignKey: true,
        references: {
          model: 'users',
          key: 'id'
        }
      },
      tourist_destination_id: {
        type: Sequelize.UUID,
        foreignKey: true,
        references: {
          model: 'tourist_destinations',
          ley: 'id'
        }
      },
      score: {
        type: Sequelize.DOUBLE(5, 2),
      },
      review: {
        type: Sequelize.TEXT,
        allowNull: true
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });
  },
  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable('Ratings');
  }
};