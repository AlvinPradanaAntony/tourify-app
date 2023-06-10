'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Rating extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      Rating.belongsTo(models.User, {
        foreignKey: 'user_id',
        onDelete: 'CASCADE',
        onUpdate: 'RESTRICT'
      })

      Rating.belongsTo(models.TouristDestination, {
        foreignKey: 'tourist_destination_id',
        onDelete: 'CASCADE',
        onUpdate: 'RESTRICT'
      })
    }
  }
  Rating.init({
    id: {
      type: DataTypes.UUID,
      primaryKey: true
    },
    user_id: {
      type: DataTypes.UUID,
      foreignKey: true
    },
    tourist_destination_id: {
      type: DataTypes.UUID,
      foreignKey: true
    },
    score: DataTypes.DOUBLE(5, 2),
    review: DataTypes.TEXT
  }, {
    sequelize,
    modelName: 'Rating',
    tableName: 'ratings'
  });
  return Rating;
};