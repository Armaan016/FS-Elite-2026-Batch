// Task 1: Demonstrate the usage of props in a functional component by creating a simple greeting component

const Greetings = ({ name }) => {
    return (
        <div>
            <h2>Hello {name}! Welcome to React.</h2>
        </div>
    );
};

export default Greetings;