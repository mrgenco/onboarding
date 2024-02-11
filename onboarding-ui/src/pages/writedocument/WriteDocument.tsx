import * as React from 'react';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';



export default function WriteDocument() {
    return (
        <Grid container spacing={2}>
            <Grid item xs={12} md={6} lg={6}>
                <Paper
                    sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    height: 240,
                    }}
                >
                </Paper>
            </Grid>
            <Grid item xs={12} md={6} lg={6}>
                <Paper
                    sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    height: 240,
                    }}
                >
                </Paper>
            </Grid>
            <Grid item xs={12}>
                <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
                </Paper>
            </Grid>
      </Grid>
    );
}