import React from 'react';
import { Route, Routes } from "react-router-dom";
import { Cart } from '../components/cart/Cart';
import Checkout from '../components/checkout/Checkout';
import { DisplayProducts } from "../components/display-products/DisplayProducts";
import { ViewProducts } from '../components/display-products/ViewProducts';
import Login from '../components/login/Login';
import Register from '../components/register/Register';
import { ViewUser } from '../components/profile-view/ProfileView';

export const AppRoutes: React.FC<unknown> = () => (
  <Routes>
    <Route path="/" element={<DisplayProducts />} />
    <Route path="/login" element={<Login />} />
    <Route path="/register" element={<Register />} />
    <Route path="/checkout" element={<Checkout />} />
    <Route path="/cart" element={<Cart />} />
    <Route path="/profile" element={<ViewUser />} />
    <Route path="/product" element={<ViewProducts/>} />
  </Routes>
)