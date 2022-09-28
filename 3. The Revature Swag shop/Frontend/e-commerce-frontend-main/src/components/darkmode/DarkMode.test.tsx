import { render, screen, fireEvent, cleanup } from "@testing-library/react";
import DarkMode from './DarkMode';
import { BrowserRouter } from "react-router-dom";
import Navbar from '../navbar/Narbar'; 

afterEach(cleanup);

test("renders dark mode component", () => {
  render(<DarkMode />);
  const inputElement = screen.getByRole("checkbox") as HTMLInputElement;
  expect(inputElement).toBeInTheDocument();
});

test("toggles dark mode", () => {
  render(<DarkMode />);
  const inputElement = screen.getByRole("checkbox") as HTMLInputElement;
  expect(inputElement.checked).toEqual(false);
  fireEvent.click(inputElement);
  expect(inputElement.checked).toEqual(true);
  expect(document.documentElement.getAttribute("data-theme")).toBe("dark");
});

test("checks toggle button", () => {
  render(<DarkMode/>);
  const inputElement = screen.getByRole("checkbox") as HTMLInputElement;
  expect(fireEvent.click(inputElement)).toBeTruthy();
});

test("Check Logo",()=>{
  render(
  <BrowserRouter>
  <Navbar/>    
  </BrowserRouter>
  );
  const logo=screen.getAllByRole("img");
  expect(logo).toBeInTheDocument;
});