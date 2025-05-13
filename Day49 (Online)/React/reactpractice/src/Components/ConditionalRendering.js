import { useState } from 'react';

const ConditionalMessage = () => {
    const [showMessage, setShowMessage] = useState(false);
    const [showBtn1, setShowBtn1] = useState(false);
    const [showBtn2, setShowBtn2] = useState(false);

    return (
        <div>
            <h2>Conditional Rendering Example</h2>

            <button onClick={() => setShowMessage(prev => !prev)}>
                {showMessage ? 'Hide Message' : 'Show Message'}
            </button>
            {showMessage && (
                <p>This is a conditional message! </p>
            )}
            <br /><br />

            <button onClick={() => setShowBtn1(prev => !prev)}>
                {showBtn1 ? 'Hide Button 1' : 'Show Button 1'}
            </button>
            <button onClick={() => setShowBtn2(prev => !prev)}>
                {showBtn2 ? 'Hide Button 2' : 'Show Button 2'}
            </button>

            {showBtn1 && showBtn2 && (
                <p>Both buttons are ON! </p>
            )}
        </div>
    );
};

export default ConditionalMessage;