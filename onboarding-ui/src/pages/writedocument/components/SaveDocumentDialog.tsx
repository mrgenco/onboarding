import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import MenuItem from '@mui/material/MenuItem';
import SendIcon from '@mui/icons-material/Send';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import { DialogActions } from '@mui/material';

export default function SaveDocumentDialog() {
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };


    const handleSave = () => {
        // api call
    };

    const [status, setStatus] = React.useState('');


    return (
        <React.Fragment>
            <Button variant="outlined" color='success' onClick={handleClickOpen}>
                Save Changes
            </Button>
            <Dialog
                open={open}
                onClose={handleClose}
                PaperProps={{
                    component: "form",
                    onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
                        event.preventDefault();
                        const formData = new FormData(event.currentTarget);
                        const formJson = Object.fromEntries((formData as any).entries());
                        console.log("formJson : ", formJson);
                        handleClose();
                    },
                }}
            >
                <DialogTitle>Save Document</DialogTitle>
                <DialogContent>
                    <Box sx={{ mt: 3 }}>
                        <Grid container spacing={2}>

                            <Grid item xs={12}>
                                <TextField
                                    name="title"
                                    required
                                    fullWidth
                                    id="title"
                                    label="Document Title"
                                    autoFocus
                                />

                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    multiline
                                    rows={4}
                                    id="summary"
                                    label="Summary"
                                    name="summary"
                                />
                            </Grid>
                            <Grid item xs={12} sm={6} >
                                <TextField
                                    id="status"
                                    select
                                    label="Status"
                                    defaultValue="0"
                                    helperText="Please select the document status"
                                >
                                    <MenuItem value={0}>Draft</MenuItem>
                                    <MenuItem value={1}>Publish</MenuItem>
                                </TextField>
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="category"
                                    label="Categories"
                                    helperText="Enter categories as comma seperated words. (Ex : Cloud,Programming,Math)"
                                    name="category"
                                />
                            </Grid>
                        </Grid>
                    </Box>
                </DialogContent>
                <DialogActions >
                    <Grid item alignItems="center">
                        <Button
                            type="submit"
                            size="large"
                            fullWidth
                            color='success'
                            variant="contained"
                            endIcon={<SendIcon />}
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Save Document
                        </Button>
                    </Grid>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );
}