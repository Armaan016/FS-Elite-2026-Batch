import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
    Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, TablePagination, TextField, Button
} from '@mui/material';

const ProductTable = () => {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(0);
    const [search, setSearch] = useState('');
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({});

    // const rowsPerPage = 10;
    const [rowsPerPage, setRowsPerPage] = useState(10)

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const res = search.trim()
                    ? await axios.get(`http://10.11.18.49:5000/products/search?name=${search}`)
                    : await axios.get(`http://10.11.18.49:5000/products`);
                setProducts(res.data);
            } catch (err) {
                console.error(err);
            }
        };
        fetchProducts();
    }, [search]);

    const handleSave = async (id) => {
        try {
            await axios.put(`http://10.11.18.49:5000/products/${id}`, editData);
            setEditingId(null);
            const res = await axios.get('http://10.11.18.49:5000/products');
            setProducts(res.data);
        } catch (err) {
            console.error('Save failed:', err);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://10.11.18.49:5000/products/${id}`);
            setProducts(products.filter(p => p._id !== id));
        } catch (err) {
            console.error('Delete failed:', err);
        }
    };



    const handleChangePage = (_, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (e) => {
        setRowsPerPage(parseInt(e.target.value, 10));
        setPage(0);
    };

    return (
        <Paper sx={{ width: '80%', margin: '2rem auto' }}>
            <TextField
                label="Search Products by Name"
                variant="outlined"
                value={search}
                onChange={(e) => setSearch(e.target.value)}
                sx={{ mb: 2 }}
                fullWidth
            />

            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow sx={{ backgroundColor: '#1976d2' }}>
                            <TableCell sx={{ color: '#fff', fontWeight: 'bold' }}>Name</TableCell>
                            <TableCell sx={{ color: '#fff', fontWeight: 'bold' }}>Price</TableCell>
                            <TableCell sx={{ color: '#fff', fontWeight: 'bold' }}>Category</TableCell>
                            <TableCell sx={{ color: '#fff', fontWeight: 'bold' }}>In Stock</TableCell>
                            <TableCell sx={{ color: '#fff', fontWeight: 'bold' }}>Actions</TableCell>
                        </TableRow>
                    </TableHead>

                    <TableBody>
                        {products
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((prod) => (
                                <TableRow key={prod._id}>
                                    {editingId === prod._id ? (
                                        <>
                                            <TableCell>
                                                <TextField
                                                    value={editData.name}
                                                    onChange={(e) => setEditData({ ...editData, name: e.target.value })}
                                                />
                                            </TableCell>
                                            <TableCell>
                                                <TextField
                                                    type="number"
                                                    value={editData.price}
                                                    onChange={(e) => setEditData({ ...editData, price: e.target.value })}
                                                />
                                            </TableCell>
                                            <TableCell>
                                                <TextField
                                                    value={editData.category}
                                                    onChange={(e) => setEditData({ ...editData, category: e.target.value })}
                                                />
                                            </TableCell>
                                            <TableCell>
                                                <select
                                                    value={editData.inStock}
                                                    onChange={(e) => setEditData({ ...editData, inStock: e.target.value === 'true' })}
                                                >
                                                    <option value="true">Yes</option>
                                                    <option value="false">No</option>
                                                </select>
                                            </TableCell>
                                            <TableCell>
                                                <Button
                                                    variant="contained"
                                                    color="success"
                                                    onClick={() => handleSave(prod._id)}
                                                >
                                                    Save
                                                </Button>
                                            </TableCell>
                                        </>
                                    ) : (
                                        <>
                                            <TableCell>{prod.name}</TableCell>
                                            <TableCell>${prod.price.toFixed(2)}</TableCell>
                                            <TableCell>{prod.category}</TableCell>
                                            <TableCell>{prod.inStock ? 'Yes' : 'No'}</TableCell>
                                            <TableCell>
                                                <Button
                                                    variant="outlined"
                                                    onClick={() => {
                                                        setEditingId(prod._id);
                                                        setEditData(prod);
                                                    }}
                                                >
                                                    Edit
                                                </Button>{' '}
                                                <Button
                                                    variant="outlined"
                                                    color="error"
                                                    onClick={() => handleDelete(prod._id)}
                                                >
                                                    Delete
                                                </Button>
                                            </TableCell>
                                        </>
                                    )}
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
