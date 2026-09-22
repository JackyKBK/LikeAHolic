import {API_URL_USERS} from "../pages/HomePage";


export const getData = async (API_URL) => {
    const response = await fetch(API_URL, {
        method: 'GET'
    });
    const parsed_data = await response.json();
    return parsed_data;
};
export const postData = async (API_URL, newData) =>{
    const response = await fetch(API_URL,{
        method: 'POST', headers: {
            'Content-type' : 'application/json',
        },
        body : JSON.stringify(newData)
    });
    const resultText = await response.text();

    if (!response.ok) {
        throw new Error(resultText || 'Failed to save post');
    }
    try {
        return JSON.parse(resultText);
    } catch (e) {
        return resultText;
    }
}
export const putData = async (API_URL, updatedData) => {
    try {
        const response = await fetch(API_URL, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedData),
        });
        if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);

        }
        return await response;
    } catch (error) {
        console.error('Error updating data:', error);
        throw error;
    }
};
export const deleteData = async (API_URL) => {
    const response = await fetch(`${API_URL}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    });
    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    console.log(response);
};