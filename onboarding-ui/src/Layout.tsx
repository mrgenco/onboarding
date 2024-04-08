import * as React from 'react';
import { styled } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar, { AppBarProps as MuiAppBarProps } from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Badge from '@mui/material/Badge';
import Container from '@mui/material/Container';
import Link from '@mui/material/Link';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import NotificationsIcon from '@mui/icons-material/Notifications';
import { mainListItems, secondListItems} from './common/listItems';
import { Outlet } from "react-router-dom";
import { useAuth } from './AuthProvider';
import { getUserInfo } from './common/GetUserInfoService';
import { LogoutOutlined } from '@mui/icons-material';

function Copyright(props: any) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const drawerWidth: number = 240;

interface AppBarProps extends MuiAppBarProps {
  open?: boolean;
}

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})<AppBarProps>(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    '& .MuiDrawer-paper': {
      position: 'relative',
      whiteSpace: 'nowrap',
      width: drawerWidth,
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
      boxSizing: 'border-box',
      ...(!open && {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
          easing: theme.transitions.easing.sharp,
          duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing(7),
        [theme.breakpoints.up('sm')]: {
          width: theme.spacing(9),
        },
      }),
    },
  }),
);

interface User{
    username: string;
    name: string;
    surname: string;
    email: string;
    password: string;
    roles: string;

}

export default function Layout() {
  const [open, setOpen] = React.useState(true);
  const [user, setUser] = React.useState<User>();

  let auth = useAuth();
  
  React.useEffect(() => {
    const fetchData = async () => {
      try {
        if (auth.isAuthenticated) {
          const response = await getUserInfo();
          setUser(response.data);
        }
      } catch (error) {
        console.error('Error fetching userinfo:', error);
      }
    };

    fetchData();
  }, [auth.isAuthenticated]);


  const toggleDrawer = () => {
    setOpen(!open);
  };

  async function handleLogout(){
    try {
      console.log("signing out..");
      await auth.signout();
    } catch (error) {
      console.error("Error occurred during signout:", error);
    }
  };
  

  return (
    <div style={{ width: '100%' }}>
      
      <Box sx={{ display: 'flex'}}>
        <CssBaseline />
        
        {auth.isAuthenticated===true && 
        <React.Fragment>
          <AppBar position="fixed" open={open}>
            <Toolbar
              sx={{
                pr: '24px', // keep right padding when drawer closed
              }}
            >
              <IconButton
                edge="start"
                color="inherit"
                aria-label="open drawer"
                onClick={toggleDrawer}
                sx={{
                  marginRight: '36px',
                  ...(open && { display: 'none' }),
                }}
              >
                <MenuIcon />
              </IconButton>
              <Typography
                component="h1"
                variant="h6"
                color="inherit"
                noWrap
                sx={{ flexGrow: 1 }}
              >
                Onboarding
              </Typography>

              <Box sx={{ display: 'flex', alignItems: 'center' }}>
                <Typography variant="subtitle1" sx={{ marginRight: 1 }}>
                  Welcome {user?.name} {user?.surname}
                </Typography>
                <IconButton color="inherit" onClick={handleLogout}>
                  <Badge color="secondary">
                    <LogoutOutlined />
                  </Badge>
                </IconButton>
              </Box>
              
            </Toolbar>
          </AppBar>
          <Drawer variant="permanent" open={open}>
            <Toolbar
              sx={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'flex-end',
                px: [1],
              }}
            >
              <IconButton onClick={toggleDrawer}>
                <ChevronLeftIcon />
              </IconButton>
            </Toolbar>
            <Divider />
            <List component="nav">
              {mainListItems}
              <Divider sx={{ my: 1 }} />
              {secondListItems}
            </List>
          </Drawer>
        </React.Fragment>
        }
        <Box
          component="main"
          sx={{
            backgroundColor: (theme) =>
              theme.palette.mode === 'light'
                ? theme.palette.grey[100]
                : theme.palette.grey[900],
            flexGrow: 1,
            height: '100vh',
            
          }}
        >
          <Container maxWidth={ false } sx={{ mt: 12, mb: 4}}>
            <Outlet/>
            <Copyright sx={{ pt: 4 }} />
          </Container>
        </Box>
      </Box>
      </div>
  );
}