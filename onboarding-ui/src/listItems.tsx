import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import DashboardIcon from '@mui/icons-material/Dashboard';
import Settings from '@mui/icons-material/Settings';
import SendIcon from '@mui/icons-material/Send';

import { Link } from "react-router-dom";
import { DocumentScanner } from '@mui/icons-material';

export const mainListItems = (
  <React.Fragment>
    <Link to="dashboard" style={{ textDecoration: 'none', color: 'inherit' }}>
      <ListItemButton>
        <ListItemIcon>
          <DashboardIcon />
        </ListItemIcon>
        <ListItemText primary="Dashboard" />
      </ListItemButton>
    </Link>
    <Link to="mydocuments" style={{ textDecoration: 'none', color: 'inherit' }}>
      <ListItemButton>
        <ListItemIcon>
          <DocumentScanner />
        </ListItemIcon>
        <ListItemText primary="My Documents" />
      </ListItemButton>
    </Link>
    <Link to="settings" style={{ textDecoration: 'none', color: 'inherit' }}>
      <ListItemButton>
        <ListItemIcon>
          <Settings />
        </ListItemIcon>
        <ListItemText primary="Settings" />
      </ListItemButton>
    </Link>
  </React.Fragment>
);

export const secondListItems = (
  <React.Fragment>
    <Link to="writedocument" style={{ textDecoration: 'none', color: 'inherit' }}>
      <ListItemButton>
        <ListItemIcon>
          <SendIcon />
        </ListItemIcon>
        <ListItemText primary="Create Document" />
      </ListItemButton>
    </Link>
  </React.Fragment>
);
