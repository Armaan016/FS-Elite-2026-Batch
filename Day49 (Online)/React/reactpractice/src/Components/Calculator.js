import { useState } from 'react';

const Calculator = () => {
    const [num1, setNum1] = useState('');
    const [num2, setNum2] = useState('');
    const [operator, setOperator] = useState('+');
    const [result, setResult] = useState('');

    const calculate = () => {
        const a = parseFloat(num1);
        const b = parseFloat(num2);

        if (isNaN(a) || isNaN(b)) {
            setResult("Invalid input");
            return;
        }

        let res;
        switch (operator) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
            case '/':
                if (b === 0) {
                    setResult("Cannot divide by zero");
                    return;
                }
                res = a / b;
                break;
            default:
                res = 0;
        }
        setResult(res);
    };

    return (
        <div>
            <h2>Calculator</h2>
            <div>
                <input
                    type="number"
                    value={num1}
                    onChange={(e) => setNum1(e.target.value)}
                    placeholder="Enter first number"
                />
                <select value={operator} onChange={(e) => setOperator(e.target.value)}>
                    <option value="+">+</option>
                    <option value="-">-</option>
                    <option value="*">*</option>
                    <option value="/">/</option>
                </select>
                <input
                    type="number"
                    value={num2}
                    onChange={(e) => setNum2(e.target.value)}
                    placeholder="Enter second number"
                />
                <button onClick={calculate}>Calculate</button>
            </div>
            {result !== '' && <p>Result: {result}</p>}
        </div>
    );
};

export default Calculator;