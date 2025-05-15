import React, { useState } from 'react';

const EmployeeForm = ({ addEmployee }) => {
    const initialFormValues = {
        fullName: '',
        email: '',
        mobile: '',
        dob: '',
        aadhar: '',
        pan: '',
        houseNo: '',
        buildingName: '',
        area: '',
        city: '',
        state: '',
        pincode: ''
    };

    const [formData, setFormData] = useState(initialFormValues);
    const [errors, setErrors] = useState({});

    const validate = () => {
        let tempErrors = {};
        if (!formData.fullName) tempErrors.fullName = "Full Name is required";
        if (!formData.email || !/\S+@\S+\.\S+/.test(formData.email)) tempErrors.email = "Valid Email is required";
        if (!formData.mobile || !/^\d{10}$/.test(formData.mobile)) tempErrors.mobile = "Valid 10-digit Mobile Number is required";
        if (!formData.dob) tempErrors.dob = "DOB is required";
        if (!formData.aadhar) tempErrors.aadhar = "AADHAR is required";
        if (!formData.pan) tempErrors.pan = "PAN is required";
        if (!formData.houseNo) tempErrors.houseNo = "House No is required";
        if (!formData.buildingName) tempErrors.buildingName = "Building Name is required";
        if (!formData.area) tempErrors.area = "Area is required";
        if (!formData.city) tempErrors.city = "City is required";
        if (!formData.state) tempErrors.state = "State is required";
        if (!formData.pincode) tempErrors.pincode = "Pincode is required";
        setErrors(tempErrors);
        return Object.keys(tempErrors).length === 0;
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validate()) {
            addEmployee(formData);
            setFormData(initialFormValues);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input name="fullName" placeholder="Full Name" value={formData.fullName} onChange={handleChange} />
            {errors.fullName && <p style={{ color: "red" }}>{errors.fullName}</p>}

            <input name="email" placeholder="Email" value={formData.email} onChange={handleChange} />
            {errors.email && <p style={{ color: "red" }}>{errors.email}</p>}

            <input name="mobile" placeholder="Mobile Number" value={formData.mobile} onChange={handleChange} />
            {errors.mobile && <p style={{ color: "red" }}>{errors.mobile}</p>}

            <input name="dob" placeholder="DOB" value={formData.dob} onChange={handleChange} />
            {errors.dob && <p style={{ color: "red" }}>{errors.dob}</p>}

            <input name="aadhar" placeholder="AADHAR" value={formData.aadhar} onChange={handleChange} />
            {errors.aadhar && <p style={{ color: "red" }}>{errors.aadhar}</p>}

            <input name="pan" placeholder="PAN" value={formData.pan} onChange={handleChange} />
            {errors.pan && <p style={{ color: "red" }}>{errors.pan}</p>}

            <input name="houseNo" placeholder="House No" value={formData.houseNo} onChange={handleChange} />
            {errors.houseNo && <p style={{ color: "red" }}>{errors.houseNo}</p>}

            <input name="buildingName" placeholder="Building Name" value={formData.buildingName} onChange={handleChange} />
            {errors.buildingName && <p style={{ color: "red" }}>{errors.buildingName}</p>}

            <input name="area" placeholder="Area" value={formData.area} onChange={handleChange} />
            {errors.area && <p style={{ color: "red" }}>{errors.area}</p>}

            <input name="city" placeholder="City" value={formData.city} onChange={handleChange} />
            {errors.city && <p style={{ color: "red" }}>{errors.city}</p>}

            <input name="state" placeholder="State" value={formData.state} onChange={handleChange} />
            {errors.state && <p style={{ color: "red" }}>{errors.state}</p>}

            <input name="pincode" placeholder="Pincode" value={formData.pincode} onChange={handleChange} />
            {errors.pincode && <p style={{ color: "red" }}>{errors.pincode}</p>}

            <button type="submit">Register Employee</button>
        </form>
    );
};

export default EmployeeForm;