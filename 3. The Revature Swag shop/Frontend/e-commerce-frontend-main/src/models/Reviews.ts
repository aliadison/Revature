export default class Reviews {
    id: number;
    score: number;
    text: string;
    user: string;
    product: number;


    constructor (id: number, score: number, text: string, user: string, product: number) {
        this.id = id;
        this.score = score;
        this.text = text;
        this.user = user;
        this.product = product;
    }
}