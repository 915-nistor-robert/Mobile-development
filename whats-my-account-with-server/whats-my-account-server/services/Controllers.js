const productService = require("../services/Service");

exports.getAllProducts = async (req, res) => {
    console.log('--------------------------')
    try {
        console.log('Start reading all products');
        const products = await productService.getAllProducts();
        res.json({data: products, status: "success"});
        console.log("Products read successfully");

    } catch (err) {
        console.log('Error in reading all products: ' + err.message);
        res.status(500).json({error: err.message});
    }
    console.log('--------------------------')
}

exports.getProductById = async (req, res) => {
    console.log('--------------------------')
    try {
        console.log('Start reading product');
        const product = await productService.getProductById(req.params.id);
        console.log(product);
        res.json({data: product, status: "success"})
        console.log('Product read successfully');
    } catch (err) {
        console.log('Error in reading product: ' + err.message);
        res.status(500).json({error: err.message});
    }
    console.log('--------------------------')
}

exports.createProduct = async (req, res) => {
    console.log('--------------------------')
    try {
        console.log('Start creating product');
        const product = await productService.createProduct(req.body['data']);
        console.log(product);
        res.json({ data: product, status: "success" });
        console.log('Product created successfully');
    } catch (err) {
        res.status(500).json({ error: err.message });
        console.log('Error in creating product: ' + err.message);
    }
    console.log('--------------------------')
};

exports.updateProduct = async (req, res) => {
    console.log('--------------------------')
    try {
        console.log('Start updating product');
        const product = await productService.updateProduct(req.body['id'], req.body['data']);
        console.log(product);
        res.json({ data: product, status: "success" });
        console.log('Product updated successfully');

    } catch (err) {
        res.status(500).json({ error: err.message });
        console.log('Error in updating product: ' + err.message);
    }
    console.log('--------------------------')
};

exports.deleteProduct = async (req, res) => {
    console.log('--------------------------')
    try {
        console.log('Start deleting product');
        const product = await productService.deleteProduct(req.params.id);
        console.log(product);
        res.json({ data: product, status: "success" });
        console.log("Product deleted successfully");
    } catch (err) {
        res.status(500).json({ error: err.message });
        console.log('Error in deleting product: ' + err.message);
    }
    console.log('--------------------------')
};