const ProductModel = require("../models/Product");
const {getProductById} = require("./Controllers");


exports.getAllProducts = async () => {
    return await ProductModel.find();
};

exports.createProduct = async (product) => {
    return await ProductModel.create(product);
};
exports.getProductById = async (id) => {
    return await ProductModel.findById(id);
};

exports.updateProduct = async (id, product) => {
    return await ProductModel.findByIdAndUpdate(id, product, {new: true});
};

exports.deleteProduct = async (id) => {
    return await ProductModel.findByIdAndDelete(id);
};