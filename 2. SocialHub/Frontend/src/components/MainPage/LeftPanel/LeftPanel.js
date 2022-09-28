import React,{useContext} from 'react';
import "./LeftPanel.css";
import LeftPanelRow from "./LeftPanelRow.js";
import {Link} from 'react-router-dom';
import PeopleIcon from '@mui/icons-material/People';
import GroupsIcon from '@mui/icons-material/Groups';
import { UserContext } from "../../../context/UserContext";

function LeftPanel() {
    const {userDetail}= useContext(UserContext);
    return (
        <div className="leftpanel">
            <Link to="/userpage"><LeftPanelRow src={userDetail.profilePic}
                title={userDetail?.accountId?.firstName} /></Link>
            <LeftPanelRow Icon={PeopleIcon} title="Friends" />
            <div className="leftpanelRow-unactive">
                <GroupsIcon />
                <h4>Popular Groups</h4>
            </div>

            <Link to="/groups/memes"><LeftPanelRow Icon={PeopleIcon} title="Memes" /></Link>
            <Link to="/groups/quotes"><LeftPanelRow Icon={PeopleIcon} title="Quotes" /></Link>
        </div>
    )
}
export default LeftPanel;