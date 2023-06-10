'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class TouristDestination extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      TouristDestination.belongsTo(models.TourCategory, {
        foreignKey: 'tour_cactegory_id',
        onDelete: 'CASCADE',
        onUpdate: 'RESTRICT'
      })

      TouristDestination.belongsTo(models.User, {
        foreignKey: 'user_id',
        onDelete: 'CASCADE',
        onUpdate: 'RESTRICT'
      })
    }
  }
  TouristDestination.init({
    id: {
      primaryKey: true,
      type: DataTypes.UUID
    },
    tour_category_id: {
      type: DataTypes.UUID,
      foreignKey: true
    },
    name: DataTypes.STRING,
    phone: DataTypes.STRING,
    email: DataTypes.STRING,
    price: DataTypes.STRING,
    description: DataTypes.TEXT,
    address: DataTypes.TEXT,
    longitude: DataTypes.STRING,
    latitude: DataTypes.STRING,
    day_open_start: DataTypes.STRING,
    day_open_end: DataTypes.STRING,
    time_open_start: DataTypes.STRING,
    time_open_end: DataTypes.STRING,
    picture: DataTypes.STRING,
    user_id: {
      type: DataTypes.UUID,
      foreignKey: true
    }
  }, {
    sequelize,
    modelName: 'TouristDestination',
    tableName: 'tourist_destinations'
  });
  return TouristDestination;
};