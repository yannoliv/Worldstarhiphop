package com.example.worldstarhiphop.artists

import android.graphics.Color
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.Transition
import com.example.worldstarhiphop.R
import com.example.worldstarhiphop.databinding.TrackItemBinding
import com.example.worldstarhiphop.network.track.Track
import android.R.attr.start
import android.animation.ObjectAnimator
import android.animation.ArgbEvaluator
import android.graphics.drawable.TransitionDrawable
import android.graphics.drawable.ColorDrawable
import android.R.attr.start



class TrackItemAdapter(
    mediaPlayerInput: MediaPlayer
) : ListAdapter<Track, TrackItemAdapter.TrackViewHolder>(
    DiffCallback
) {

    private var mediaPlayer = mediaPlayerInput

    // Onthouden wat de laatste positie was
    private var mPosition = -1
    // Voorlaatste positie
    private var vPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackViewHolder {
        return TrackViewHolder(
            TrackItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {

        val track = getItem(position)
        track.rank = position +1

        // maximum 1 item tegelijk kunnen selecteren in een recyclerview
        // Meer info: https://stackoverflow.com/questions/47707969/how-to-show-single-item-selected-in-recyclerview-using-kotlin
        if (mPosition == position)
        {
            // set selected here
            holder.focusTrack()
        } else
        {
            //set unselected here
            if(vPosition == position){
                holder.unfocusTrack()
            }
        }

        // Klik op de balk van het liedje
        holder.binding.liedjeBalk.setOnClickListener(View.OnClickListener {
            // Oftewel focussed de balk als je op een nieuwe track klikt
            // OF als de mediaplayer gepauzeert is, en je klikt op de balk wiens pauzeknop
            // je net hebt ingedrukt.
            if(mPosition != position || mPosition == position && !mediaPlayer.isPlaying){
                holder.initialiseerLiedje(track, mediaPlayer)
                mediaPlayer.start()

                // Als liedje gedaan heeft met spelen:
                mediaPlayer.setOnCompletionListener {
                    holder.unfocusTrack()
                }

                vPosition = mPosition
                mPosition = position
                notifyDataSetChanged();
            }
        })


        // Klik op de pauze knop van het liedje
        holder.binding.relativeLayout.setOnClickListener(View.OnClickListener {
            if(mPosition == position){
                if(mediaPlayer.isPlaying){
                    mediaPlayer.pause()
                    holder.unfocusTrack()
                    // De balk is niet meer gefocust en posities moeten dus gereset worden
                    vPosition = -1
                    mPosition = -1
                } else{
                    holder.focusTrack()
                    vPosition = mPosition
                    mPosition = position
                    notifyDataSetChanged();
                    mediaPlayer.start()
                }
            } else{
                holder.initialiseerLiedje(track, mediaPlayer)
                holder.focusTrack()
                // Posities goed steken. vPos = voorlaatste, mPos= laatste
                vPosition = mPosition
                mPosition = position
                notifyDataSetChanged();
                mediaPlayer.start()
            }

        })

        // favoriete track
        holder.binding.favoriteTrack.setOnClickListener(View.OnClickListener{

        })

        holder.bind(track)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class TrackViewHolder(private var bindingInput: TrackItemBinding):
        RecyclerView.ViewHolder(bindingInput.root) {

        val binding = bindingInput

        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()
        }

        fun focusTrack() {
            binding.imageViewPlayPause.setImageResource(R.drawable.ic_pause_black_24dp)

            objectAnimatorFunctie(binding.liedjeBalk, "backgroundColor", Color.WHITE, Color.parseColor("#991a1a"))
            objectAnimatorFunctie(binding.favoriteTrack, "colorFilter", Color.DKGRAY, Color.WHITE)
            objectAnimatorFunctie(binding.nummer, "textColor", Color.DKGRAY, Color.WHITE)
            objectAnimatorFunctie(binding.titel, "textColor", Color.DKGRAY, Color.WHITE)
            objectAnimatorFunctie(binding.imageViewPlayPause, "colorFilter", Color.DKGRAY, Color.WHITE)
            objectAnimatorFunctie(binding.imageViewPlayPauseCircle, "colorFilter", Color.DKGRAY, Color.WHITE)
        }

        fun unfocusTrack(){
            binding.imageViewPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp)

            objectAnimatorFunctie(binding.liedjeBalk, "backgroundColor", Color.parseColor("#991a1a"), Color.WHITE)
            objectAnimatorFunctie(binding.favoriteTrack, "colorFilter", Color.WHITE, Color.DKGRAY)
            objectAnimatorFunctie(binding.nummer, "textColor", Color.WHITE, Color.DKGRAY)
            objectAnimatorFunctie(binding.titel, "textColor", Color.WHITE, Color.DKGRAY)
            objectAnimatorFunctie(binding.imageViewPlayPause, "colorFilter", Color.WHITE, Color.DKGRAY)
            objectAnimatorFunctie(binding.imageViewPlayPauseCircle, "colorFilter", Color.WHITE, Color.DKGRAY)
        }


        fun initialiseerLiedje(track: Track, mediaPlayer: MediaPlayer){
            try {
                mediaPlayer.reset()
                mediaPlayer.setVolume( 1.0f,1.0f)
                mediaPlayer.setDataSource(track.preview)
                mediaPlayer.prepare()
            } catch (e: Exception) {
                print(e)
            }
        }

        private fun objectAnimatorFunctie(view: View, propertyName: String, startColor: Int, endColor: Int){
            ObjectAnimator.ofObject(
                view, // Object to animating
                propertyName, // Property to animate
                ArgbEvaluator(), // Interpolation function
                startColor, // Start color
                endColor // End color
            ).setDuration(200) // Duration in milliseconds
            .start() // Finally, start the anmation
        }

    }

}


/*
               if(selectedPosition == position){
                    if(!mediaPlayer.isPlaying){
                        initialiseerLiedje(track,mediaPlayer)
                        focusTrack()
                        mediaPlayer.start()
                    } else{

                    }
                }else{
                    unfocusTrack()
                }

 */

/*
            // Klik op de pauze knop van het liedje
            binding.imageViewPlayPause.setOnClickListener(View.OnClickListener {

                if(mediaPlayer.isPlaying){
                    unfocusTrack()
                    mediaPlayer.pause()
                } else{
                    focusTrack()
                    mediaPlayer.start()
                }
            })

 */