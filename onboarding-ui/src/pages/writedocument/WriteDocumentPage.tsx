import React, { useState } from 'react';
import Grid from '@mui/material/Grid';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import SaveDocumentDialog from './components/SaveDocumentDialog';
import Markdown from './components/DocumentEditor';
import Preview from './components/DocumentPreview';

export default function WriteDocument() {
    const [markdown, setMarkdown] = useState('');
    const [viewMode, setViewMode] = useState('both');

    const handleViewModeChange = (event: SelectChangeEvent) => {
        setViewMode(event.target.value as string);
    };

    return (
        <Grid container spacing={2}>
            <Grid item xs={6}>
                <SaveDocumentDialog markdown={markdown} />
            </Grid>
            <Grid item xs={6}>
                <FormControl variant="outlined">
                    <InputLabel id="view-mode-label">View Mode</InputLabel>
                    <Select
                        labelId="view-mode-label"
                        value={viewMode}
                        onChange={handleViewModeChange}
                        label="View Mode"
                    >
                        <MenuItem value="markdown">Markdown Only</MenuItem>
                        <MenuItem value="preview">Preview Only</MenuItem>
                        <MenuItem value="both">Markdown & Preview</MenuItem>
                    </Select>
                </FormControl>
            </Grid>
            {(viewMode === 'both' || viewMode === 'markdown') && (
                <Grid item xs={12} md={6} lg={6}>
                    <Markdown onChange={setMarkdown} />
                </Grid>
            )}
            {(viewMode === 'both' || viewMode === 'preview') && (
                <Grid item xs={12} md={6} lg={6}>
                    <Preview markdown={markdown} />
                </Grid>
            )}
        </Grid>
    );
}
