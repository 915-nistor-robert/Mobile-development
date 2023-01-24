const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const productSchema = new Schema({
    name: String,
    quantity: Number,
    desiredQuantity: Number,
    measurement: String,
    expirationDate: String,
    price: Number
});

module.exports = mongoose.model("Product", productSchema);