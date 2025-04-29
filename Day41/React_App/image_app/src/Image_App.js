import React, { useState } from 'react';
import './App.css';
import sampleImage from "C:\\Users\\raufu\\Pictures\\Jeddah Corniche pic.jpg";

function App() {
    const [scale, setScale] = useState(1);

    const zoomIn = () => {
        setScale((prev) => Math.min(prev + 0.1, 3)); // cap at 3x
    };

    const zoomOut = () => {
        setScale((prev) => Math.max(prev - 0.1, 0.5)); // min at 0.5x
    };

    const reset = () => {
        setScale(1);
    };

    return (
        <div
            className="App"
            style={{
                padding: '20px',
                backgroundColor: '#121212', // Dark background
                color: '#ffffff', // White text
                minHeight: '100vh',
            }}
        >
            <h2>🖼️ Image Zoom App</h2>
            <div style={{ marginTop: '20px', marginBottom: '20px' }}>
                <button
                    onClick={zoomIn}
                    style={{
                        padding: '10px 20px',
                        margin: '5px',
                        backgroundColor: '#4CAF50', // Green button
                        color: 'white',
                        border: 'none',
                        borderRadius: '5px',
                        cursor: 'pointer',
                        fontSize: '16px',
                    }}
                >
                    Zoom In 🔍
                </button>{' '}
                <button
                    onClick={zoomOut}
                    style={{
                        padding: '10px 20px',
                        margin: '5px',
                        backgroundColor: '#f44336', // Red button
                        color: 'white',
                        border: 'none',
                        borderRadius: '5px',
                        cursor: 'pointer',
                        fontSize: '16px',
                    }}
                >
                    Zoom Out 🔎
                </button>{' '}
                <button
                    onClick={reset}
                    style={{
                        padding: '10px 20px',
                        margin: '5px',
                        backgroundColor: '#008CBA', // Blue button
                        color: 'white',
                        border: 'none',
                        borderRadius: '5px',
                        cursor: 'pointer',
                        fontSize: '16px',
                    }}
                >
                    Reset 🔁
                </button>
            </div>

            <div
                className="image-container"
                style={{
                    overflow: 'hidden',
                    width: '600px',
                    margin: 'auto',
                    border: '2px solid #ffffff', // White border for the image container
                    borderRadius: '10px',
                }}
            >
                <img
                    src={sampleImage}
                    alt="Zoomable"
                    style={{
                        transition: 'transform 0.3s ease',
                        transform: `scale(${scale})`,
                        transformOrigin: 'center center',
                        width: '100%',
                    }}
                />
            </div>
        </div>
    );
}

export default App;