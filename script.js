import http from 'k6/http';

const url = 'http://localhost:8080/api/pix';

const requestData = {
    "name": "Marinho",
    "quantity": 1,
    "amount": "10.5"
};


export default function () {
    const headers = {
        'Content-Type': 'application/json',
    };

    // Faça a solicitação HTTP POST com os dados do objeto
    const response = http.post(url, JSON.stringify(requestData), { headers });
}
