export default class Product {
    id: number;
    name: string;
    quantity: number;
    reviewavg: number;
    price: number;
    description: string;
    image: string;

    constructor (id: number, name: string, quantity: number, reviewavg: number, description: string, price: number, image: string) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.reviewavg = reviewavg;
        this.description = description;
        this.price = price;
        this.image = image;
    }
}