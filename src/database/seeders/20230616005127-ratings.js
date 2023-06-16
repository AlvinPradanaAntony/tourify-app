'use strict';
const { v4 } = require('uuid')

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
    function getRandomInt(max) {
      const rndInt = Math.floor(Math.random() * max) + 1;
      return rndInt
    }

    const tourDestination = [
      "0fd7735f-f280-4d34-b8b4-76f2d57ade1e",
      "19d26245-2984-4d53-a87c-efd93af1bfe3",
      "1c87a9ff-b824-4455-af4e-c1042fbadfcf",
      "1ce04cd8-c74b-4531-b88a-def270d4509a",
      "2027b8ef-062b-4722-b387-9f2695af07cd",
      "2125612f-7704-4062-bc75-67918604fc86",
      "25122292-2f89-4187-b341-4d6964c83b10",
      "299b9782-b70a-456a-9d39-0cad04c6e76b",
      "3786c733-fadf-41fb-8268-5762cedc0197",
      "3bbf5b60-722a-49e8-b421-ce642bad51dd",
      "4efad075-c3b8-448a-a4ae-a014868aadd4",
      "63ed3fa6-731c-47cb-bea4-39a2195cea9a",
      "6411d777-15d4-41f9-95ad-54bbd42f9098",
      "67b1bd6b-900a-470e-a430-55658ee41c64",
      "6c328d10-c614-46ca-84ee-6f2d09a884c9",
      "816f2845-8806-4575-bb0e-c88cb9a9ed3e",
      "9352af34-beec-4014-b648-903c57205daa",
      "9c3343fd-adbe-4e62-a402-4a14b39463b7",
      "a6bbc049-56ca-4cff-9d86-f7729141a511",
      "acd10cbe-0b6d-4e83-aaba-23bfd4ace123",
      "aeb6b28a-58c4-4a3d-8728-584ed8c33def",
      "b0abb98d-6b95-4846-9885-5f7962a4be88",
      "ba901e87-48a8-4199-ba8c-e533b72a6892",
      "bc0e1b80-6e34-4c5d-a087-0af7c139598b",
      "bcd36928-b7d8-41fc-99ab-56da8fa3bcbb",
      "bef01cbf-422e-44ff-bca4-d14191403a43",
      "c260d494-30e5-43af-a1b8-a2e65908b453",
      "cac1699b-9a44-40e2-9d4b-abe62467e387",
      "cc967c96-39fd-4f53-b82c-dc5a19c9c846",
      "d06b14c7-89f0-4c8f-94db-87becd3e2750",
      "d1efa3f6-938d-4a26-ad36-89a5bfa071be",
      "e010c778-c917-4a64-867f-db2a80c36828",
      "eafba499-e505-4d79-9054-560e5eff23cb",
      "f790f12b-65ae-4f9f-a029-23c8eaaa6133",
      "f901859d-e7ff-45f6-a72f-03cb0d1b4054",
      "f9eeedf0-058a-4579-b138-a4801d24ff8c",
    ]
    const users = [
      'bd4faa79-04b8-4c89-8003-43330b1a9fc2',
      'bd4faa79-04b8-4c89-8003-772ujhu27717',
      'bd4faa79-04b8-1827-8003-28817b1a9fc2',
      'a73naa79-18w8-4c89-8003-43330b1a99o3',
      'a73n89sa-18w8-4c89-8003-43330b1a99o3',
      'a73naa79-18w8-4c89-8003-71yhs88293e3',
    ]
    var data = []
    for (var i = 0; i <= 5000; i++) {
      data.push({
        user_id: users[getRandomInt(users.length)],
        tourist_destination_id: getRandomInt(tourDestination.length),
        score: getRandomInt(6),
        review: 'Lorem ipsum dolor sit amet',
        createdAt: new Date(),
        updatedAt: new Date()
      })
    }

    return queryInterface.bulkInsert('ratings', data)
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
