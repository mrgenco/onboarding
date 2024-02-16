import * as React from 'react';
import Grid from '@mui/material/Grid';
import SaveDocumentDialog from './components/SaveDocumentDialog'
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';

export default function WriteDocument() {
    return (
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <SaveDocumentDialog />
            </Grid>
            <Grid item xs={12} md={6} lg={6}>
                <TextField
                    id="filled-multiline-static"
                    label="Write Your Document Here"
                    multiline
                    fullWidth
                    defaultValue="test"
                />
            </Grid>
            <Grid item xs={12} md={6} lg={6}>
                <TextField
                    fullWidth
                    id="filled-multiline-static"
                    label="Preview"
                    multiline
                    defaultValue=""
                />
            </Grid>
            <Grid item xs={12} md={6} lg={6}>

            </Grid>
        </Grid>
    );
}