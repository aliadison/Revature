import React, { Component } from 'react';
import { Avatar } from '@mui/material';
import "./ImageLayout.css";
import Badge from '@mui/material/Badge';
import { getImage } from '../../../GetImage';

class ImageLayout extends Component {
    constructor(props) {
        super(props);
        this.state = { }
    }
    render() { 
        return (
            <div className="imagelayout__container">
                <div className="imagelayout__imglay">
                    {
                        this.props.status ?
                        <Badge color="secondary" overlap="circle" badgeContent=" " variant="dot">
                            <Avatar className="imagelayout__img" src={getImage(this.props.image)} />
                        </Badge>
                        : this.props.status===false ?
                        <Avatar className="imagelayout__img" src={getImage(this.props.image)} />
                        : <Avatar className="imagelayout__img" src={this.props.image} />
                    }
                </div>
                <div className="imagelayout__text">
                    {this.props.text}
                </div>
            </div>
        );
    }
}
 
export default ImageLayout;