import { Avatar } from "@mui/material";
import React from 'react';
import './LeftPanelRow.css';

// Capitalize component so it'll pass as prop
function LeftPanelRow({ src, Icon, title }) {
  return (
    <div className="leftpanelRow">
        {src && <Avatar src={src} />}
        {Icon && <Icon />}
            <h4>{title}</h4>
    </div>
  )
}

export default LeftPanelRow;