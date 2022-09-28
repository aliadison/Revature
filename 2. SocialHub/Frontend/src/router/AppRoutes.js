import React from 'react';
import { Route, Routes } from "react-router-dom";
import { MainPage } from '../components/MainPage/MainPage';
import {Login} from '../components/Login/Login'
import { Memes } from '../components/Memes/Memes';
import { UserPage } from '../components/UserPage/UserPage'
import { Quotes } from '../components/Quotes/Quotes';

export const AppRoutes = () => (
  
  <Routes>
    <Route path="/login" element={<Login />} />
    <Route path="/" element={<MainPage />} />
    <Route path="/groups/memes" element={<Memes />} />
    <Route path="/groups/quotes" element={<Quotes />} />
    <Route path="/userpage" element={<UserPage />} />
  </Routes>
)