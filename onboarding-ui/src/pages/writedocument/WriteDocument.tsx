import React, { useState } from 'react';
import Grid from '@mui/material/Grid';
import SaveDocumentDialog from './components/SaveDocumentDialog'
import TextField from '@mui/material/TextField';
import Markdown from './components/Markdown';
import Preview from './components/Preview';

export default function WriteDocument() {

    const [markdown, setMarkdown] = useState('');
    return (
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <SaveDocumentDialog />
            </Grid>
            <Grid item xs={12} md={6} lg={6}>
                <Markdown onChange={setMarkdown} />
            </Grid>
            <Grid item xs={12} md={6} lg={6}>
                <Preview markdown={markdown} />
            </Grid>
        </Grid>
    );
}