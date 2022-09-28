import React, { Component } from 'react';
import "./RightPanel.css";
import ImageLayout from "../ImageLayout/ImageLayout";

class RightPanel extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            data : []
        }
    }
    render() { 
        return (
            <div className="rightpanel__container">
                <div className="rightpanel__header">
                    <h4>Contacts</h4>
                </div>
                <div className="rightpanel__content">
                    {
                        this.state.data.map((item) =>(
                            <ImageLayout iamge={item.userImage} status={item.active} text={item.username} />
                        ))
                    }
                </div>
            </div>
        );
    }
}
 
export default RightPanel;
