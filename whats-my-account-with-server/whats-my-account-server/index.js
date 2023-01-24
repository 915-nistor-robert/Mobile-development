const productService = require("./services/Service")

console.log('Port: 8080');

const mongoose = require("mongoose");
const uri = "mongodb+srv://admin:admin@cluster0.3gal9i8.mongodb.net/?retryWrites=true&w=majority"

mongoose.connect(uri, {
        useNewUrlParser: true,
        useUnifiedTopology: true,
    })
    .then(() => {
        console.log("Connected to MongoDB");
    })
    .catch((err) => {
        console.log(err);
    });

const express = require('express');
const cors = require('cors');
const app = express();
const server = require('http').createServer(app);
const io = require('socket.io')(server, {
    cors: {
        origin: "*"
    }
});

const router = require("./routes/Routes");
app.use(cors())
app.use(express.json());
app.use("/api/products", router);

server.listen(8080, () => {
    console.log('Server started on port 8080');
});

io.on('connection', (socket) => {
    socket.on('identifier', async (data) => {
        const id = data['id'];
        console.log('Client ' + id + ' connected to port 8080 through websocket.')
    });


});

// const wss = new WebSocket.Server({ port: 8080 });

// wss.on('connection', (socket) => {
//     socket.on('message', async (message) => {
//         const data = JSON.parse(message);
//         console.log('Port: 8000');
//
//         if (data.type === 'create') {
//             try {
//                 const product = await productService.createProduct(data.data);
//                 console.log("success");
//                 socket.send("success");
//             } catch (err) {
//                 socket.send(err.message)
//             }
//         } else if (data.type === 'readOne') {
//
//         } else if (data.type === 'readAll') {
//           const products = await productService.getAllProducts();
//           socket.send(products);
//         } else if (data.type === 'update') {
//
//         } else if (data.type === 'delete') {
//
//         }
//     })
// })