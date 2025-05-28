import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, TablePagination
} from '@mui/material';

const ProductTable = () => {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(0);
    // const rowsPerPage = 10;
    const [rowsPerPage, setRowsPerPage] = useState(10) 

    useEffect(() => {
        axios.get('http://localhost:5000/products')
            .then(res => setProducts(res.data))
            .catch(err => console.error(err));
    }, []);

    const handleChangePage = (_, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (e) => {
        setRowsPerPage(parseInt(e.target.value, 10));
        setPage(0);
    };

    return (
        <Paper sx={{ width: '80%', margin: '2rem auto' }}>
            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell><strong>Name</strong></TableCell>
                            <TableCell><strong>Price</strong></TableCell>
                            <TableCell><strong>Category</strong></TableCell>
                            <TableCell><strong>In Stock</strong></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {products
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((prod, index) => (
                                <TableRow key={index}>
                                    <TableCell>{prod.name}</TableCell>
                                    <TableCell>${prod.price.toFixed(2)}</TableCell>
                                    <TableCell>{prod.category}</TableCell>
                                    <TableCell>{prod.inStock ? 'Yes' : 'No'}</TableCell>
                                </TableRow>
                            ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <TablePagination
                component="div"
                count={products.length}
                page={page}
                onPageChange={handleChangePage}
                rowsPerPage={rowsPerPage}
                onRowsPerPageChange={handleChangeRowsPerPage}
                rowsPerPageOptions={[10, 25, 50]} 
            />
        </Paper>
    );
};

export default ProductTable;
