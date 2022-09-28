import { Badge } from "@material-ui/core";
import { ShoppingCartOutlined } from "@material-ui/icons";
import React from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import DarkMode from "../darkmode/DarkMode";

const Container = styled.div`
  height: 60px;
`;

const Wrapper = styled.div`
  padding: 10px 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const Left = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
`;

const Logo = styled.h1`
  font-weight: bold;
`;

const url='https://cdn.discordapp.com/attachments/1014202573328236664/1016779501922947205/unnamed.png';
const LogoImage = styled.img`
  width:10;
  height:5;
`;
LogoImage.defaultProps = {
  src: url
};

const Right = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: flex-end;
`;

const MenuItem = styled.div`
  font-size: 14px;
  cursor: pointer;
  margin-left: 25px;
`;

const Navbar = () => {
  const navigate = useNavigate();

  return (
    <Container>
      <Wrapper>
        <Left>
        {/* <Logo onClick={() => {navigate('/')}}>Revature Swag Shop</Logo> */}
        <LogoImage onClick={() => {navigate('/')}}/>
        </Left>
        <Right>
          <MenuItem onClick={() => {navigate('/register')}}>REGISTER</MenuItem>
          <MenuItem onClick={() => {navigate('/login')}}>SIGN IN</MenuItem>
          <MenuItem onClick={() => {navigate('/profile')}}>PROFILE</MenuItem>
          <MenuItem onClick={() => {navigate('/cart')}}>
            <Badge color="primary">
              <ShoppingCartOutlined />
            </Badge>
          </MenuItem>
          <MenuItem>        
            <DarkMode/>
          </MenuItem>
        </Right>
      </Wrapper>
    </Container>
  );
};

export default Navbar;