import React, { useState, useRef, useEffect } from 'react';
import './App.css';

const images = [
  "/1.webp",
  "/2.webp",
  "/3.webp",
  "/4.webp",
  "/5.webp",
  "/6.webp",
];

function App() {
  const [lightboxOpen, setLightboxOpen] = useState(false);
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const lightboxRef = useRef(null); // Reference for the lightbox

  const openLightbox = (index) => {
    setCurrentImageIndex(index);
    setLightboxOpen(true);
  };

  const closeLightbox = () => {
    setLightboxOpen(false);
  };

  const nextImage = () => {
    setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
  };

  const prevImage = () => {
    setCurrentImageIndex((prevIndex) =>
      prevIndex === 0 ? images.length - 1 : prevIndex - 1
    );
  };

  const handleKeyDown = (e) => {
    if (e.key === "ArrowRight") {
      nextImage();
    } else if (e.key === "ArrowLeft") {
      prevImage();
    } else if (e.key === "Escape") {
      closeLightbox();
    }
  };

  // Automatically focus the lightbox when it opens
  useEffect(() => {
    if (lightboxOpen && lightboxRef.current) {
      lightboxRef.current.focus();
    }
  }, [lightboxOpen]);

  return (
    <>
      <div
        className="App"
        style={{
          padding: '20px',
          backgroundColor: '#121212',
          color: '#ffffff',
          minHeight: '100vh',
          filter: lightboxOpen ? 'blur(5px)' : 'none',
          transition: 'filter 0.3s ease',
        }}
      >
        <h2>🖼️ Image Gallery</h2>
        <div
          className="gallery"
          style={{
            display: 'flex',
            flexWrap: 'wrap',
            gap: '10px',
            justifyContent: 'center',
          }}
        >
          {images.map((image, index) => (
            <img
              key={index}
              src={image}
              alt={`Thumbnail ${index + 1}`}
              onClick={() => openLightbox(index)}
              style={{
                width: '350px',
                height: '220px',
                objectFit: 'cover',
                cursor: 'pointer',
                border: '2px solid #ffffff',
                borderRadius: '5px',
              }}
            />
          ))}
        </div>
      </div>

      {lightboxOpen && (
        <div
          className="lightbox"
          ref={lightboxRef} // Attach the ref to the lightbox
          style={{
            position: 'fixed',
            top: 0,
            left: 0,
            width: '100%',
            height: '100%',
            backgroundColor: 'rgba(0, 0, 0, 0.9)',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            zIndex: 1000,
          }}
          onKeyDown={handleKeyDown}
          tabIndex={0} // Make the lightbox focusable
        >
          <button
            onClick={prevImage}
            style={{
              position: 'absolute',
              left: '20px',
              top: '50%',
              transform: 'translateY(-50%)',
              backgroundColor: 'transparent',
              color: '#ffffff',
              fontSize: '24px',
              border: 'none',
              cursor: 'pointer',
            }}
          >
            ◀
          </button>
          <img
            src={images[currentImageIndex]}
            alt={`Lightbox ${currentImageIndex + 1}`}
            style={{
              maxWidth: '80%',
              maxHeight: '80%',
              borderRadius: '10px',
            }}
          />
          <button
            onClick={nextImage}
            style={{
              position: 'absolute',
              right: '20px',
              top: '50%',
              transform: 'translateY(-50%)',
              backgroundColor: 'transparent',
              color: '#ffffff',
              fontSize: '24px',
              border: 'none',
              cursor: 'pointer',
            }}
          >
            ▶
          </button>
          <button
            onClick={closeLightbox}
            style={{
              position: 'absolute',
              top: '20px',
              right: '20px',
              backgroundColor: 'transparent',
              color: '#ffffff',
              fontSize: '24px',
              border: 'none',
              cursor: 'pointer',
            }}
          >
            ✖
          </button>
        </div>
      )}
    </>
  );
}

export default App;