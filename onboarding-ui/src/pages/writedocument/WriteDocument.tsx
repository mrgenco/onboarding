import * as React from 'react';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import SaveDocumentDialog from './components/SaveDocumentDialog'



export default function WriteDocument() {
    return (
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <SaveDocumentDialog/>
            </Grid>
            <Grid item xs={12} md={6} lg={6}>
              
            </Grid>
            <Grid item xs={12} md={6} lg={6}>
                
            </Grid>
      </Grid>
    );
}